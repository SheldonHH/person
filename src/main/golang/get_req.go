package main

import (
    "fmt"
    "log"
    "net/http"
//     "bytes"
//     "encoding/json"
    "strings"
    "io/ioutil"
    "net/url"
)

func main() {
//     resp, err := http.Get("http://127.0.0.1:6001/api/v1/person/st")
//     if err != nil {
//         log.Fatal(err)
//     }
//     fmt.Println(resp)
//     defer resp.Body.Close()
//     body, err := ioutil.ReadAll(resp.Body)
//
//     if err != nil {
//         log.Fatal(err)
//     }
//
//     fmt.Println(string(body))
//


    nz := "/Users/mac/singapore/person1/src/main/python/data_sample/small_data.csv"
    nnz := strings.Replace(nz, "/", "!", -1)
    fmt.Println(nnz)
    params := "datapath=" + url.QueryEscape(nnz)
    path := fmt.Sprintf("http://127.0.0.1:6001/api/v1/person/%s", params)
    resp, err := http.Get(path)

    if err != nil {
        log.Fatal(err)
    }
    defer resp.Body.Close()
    body, err := ioutil.ReadAll(resp.Body)
    fmt.Println(string(body))


//     values := map[string]string{"path": "/Users/mac/singapore/person1/src/main/python/data_sample/small_data.csv"}
//     json_data, err := json.Marshal(values)
//     if err != nil {
//         log.Fatal(err)
//     }
//     resp, err := http.Post("http://127.0.0.1:6001/api/v1/person/p4psim/", "application/json",
//         bytes.NewBuffer(json_data))
//
//     if err != nil {
//         log.Fatal(err)
//     }
//     var res map[string]interface{}
//     json.NewDecoder(resp.Body).Decode(&res)
//     fmt.Println(res["json"])

    //     if err != nil {
    //         log.Fatal(err)
    //     }
    //     fmt.Println(resp)
    //     defer resp.Body.Close()
    //
    //     body, err := ioutil.ReadAll(resp.Body)
    //
    //     if err != nil {
    //         log.Fatal(err)
    //     }
    //
    //     fmt.Println(string(body))


}