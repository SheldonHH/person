package main

import (
    "bytes"
    "encoding/json"
//     "fmt"
    "log"
    "net/http"
)

func main() {

    values := map[string]string{"data_path": "/Users/mac/singapore/person1/src/main/resources/data_sample/small_data.csv"}
    json_data, err := json.Marshal(values)

    if err != nil {
        log.Fatal(err)
    }

    resp, err := http.Post("http://127.0.0.1:6001/finalP4P", "application/json",
        bytes.NewBuffer(json_data))

    if err != nil {
        log.Fatal(err)
    }

    var res map[string]interface{}
    fmt.print(resp)
    json.NewDecoder(resp.Body).Decode(&res)

}