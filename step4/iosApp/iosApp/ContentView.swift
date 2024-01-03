import SwiftUI
import shared

struct ContentView: View {
    let phrases = Greeting().greet()

    var body: some View {
        List(phrases, id: \.self) {
            Text($0)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
