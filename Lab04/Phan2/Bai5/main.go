package main

import (
	"fmt"
	"net/http"
)

func homeHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintln(w, "Xin chao! Day la ung dung Go chay trong Docker.")
	fmt.Fprintln(w, "Server dang hoat dong tren cong 8080.")
}

func main() {
	http.HandleFunc("/", homeHandler)
	fmt.Println("Server dang chay tai http://localhost:8080")
	http.ListenAndServe(":8080", nil)
}
