import SwiftUI
import SharedLogic

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
            for await phrase in Greeting().greet() {
                self.greetings.append(phrase)
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
