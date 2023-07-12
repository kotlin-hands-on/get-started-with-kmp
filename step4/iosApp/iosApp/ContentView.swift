import SwiftUI
import shared
import KMPNativeCoroutinesAsync
import KMPNativeCoroutinesCore

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel

    var body: some View {
        NavigationView {
            listView()
            .onAppear{self.viewModel.startObserving()}
        }
    }

    private func listView() -> AnyView {
        switch viewModel.greetings {
        case .loading:
            return AnyView(Text("Loading...").multilineTextAlignment(.center))
        case .result(let greetingMessages):
            return AnyView(VStack {
                List {
                    ForEach(greetingMessages, id: \.self) { greeting in
                        Text(greeting)
                    }
                }
            })
        case .error(let description):
            return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }

}

extension ContentView {
    enum LoadableMessages {
        case loading
        case result([String])
        case error(String)
    }
    
    @MainActor
    class ViewModel: ObservableObject {
        @Published var greetings = LoadableMessages.loading
        
        func startObserving() {
            Task {
                do {
                    let stream = asyncStream(for: Greeting().greetNative())
                    for try await data in stream {
                        self.greetings = .result(data)
                    }
                } catch {
                    print("Failed with error: \(error)")
                    self.greetings = .error("Error")
                }
            }
        }
    }
}
