import SwiftUI
import Shared
import KMPNativeCoroutinesCore
import KMPNativeCoroutinesAsync

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel

    var body: some View {
        ListView(phrases: viewModel.greetings)
            .task { await self.viewModel.startObserving() }
    }
}

extension ContentView {
    @MainActor
    class ViewModel: ObservableObject {
        @Published var greetings: Array<String> = []

        func startObserving() async {
            do {
                let sequence = asyncSequence(for: Greeting().greet())
                for try await phrase in sequence {
                    self.greetings.append(phrase)
                }
            } catch {
                print("Failed with error: \(error)")
            }
        }
    }
}

struct ListView: View {
    let phrases: Array<String>

    var body: some View {
        List(phrases, id: \.self) {
            Text($0)
        }
    }
}
