import SwiftUI
import SharedLogic
import KMPNativeCoroutinesAsync
import KMPNativeCoroutinesCore

struct ContentView: View {
    // Subscribes the view to the view model
    // that is declared below as ObservableObject
    @ObservedObject private(set) var viewModel: ViewModel

    var body: some View {
        ListView(phrases: viewModel.greetings)
            // Calls the startObserving() function
            // with the .task modifier to support concurrency
            .task { await self.viewModel.startObserving() }
    }
}

// ViewModel is declared as an extension to ContentView,
// as they are closely connected
extension ContentView {
    @MainActor
    class ViewModel: ObservableObject {
        // This property is intended to hold the greeting phrases
        // emitted by the ViewModel's flow
        @Published var greetings: [String] = []
        
        func startObserving() async {
            do {
                // Consumes the flow emitted by Greeting().greet() from Kotlin
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
