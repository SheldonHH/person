package main

import (
    "fmt"
    "log"
    "net/http"
    "strings"
    "io/ioutil"
    "net/url"
    "time"
    "crypto/rand"
)


func elapsed(what string) func() {
	start := time.Now()
	return func() {
		fmt.Printf("%s took %v\n", what, time.Since(start))
	}
}

func main() {
//     raw_path := "/Users/mac/singapore/person1/src/main/python/data_sample/user_1_data.csv"
    raw_path := "/Users/mac/singapore/person1/src/main/python/data_sample/small_data2.csv"
    prepared_raw_path := strings.Replace(raw_path, "/", "!", -1)
    b := make([]byte, 16)
        _, err := rand.Read(b)
        if err != nil {
            fmt.Println("Error: ", err)
            return
        }
//     uuid_str := fmt.Sprintf("%X-%X-%X-%X-%X", b[0:4], b[4:6], b[6:8], b[8:10], b[10:])
    uuid_str := "0c1e1494-aa4a-4afa-b494-d49754b0e244"
    params := "datapath=" + url.QueryEscape(prepared_raw_path) +"=personID=" + url.QueryEscape(uuid_str)
    path := fmt.Sprintf("http://127.0.0.1:6002/api/v1/person/%s", params)
    resp, err := http.Get(path)
    defer elapsed("p4p")()
    if err != nil {
        log.Fatal(err)
    }
    defer resp.Body.Close()
    body, err := ioutil.ReadAll(resp.Body)
    fmt.Println(string(body))

}