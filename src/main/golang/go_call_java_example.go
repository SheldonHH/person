package main

import (
    "os/exec"
)
string data_path = "/root/user_data1.csv"
func main() {
    cmd := exec.Command("java", "-jar", "p4p.jar", data_path)
    if err := cmd.Run(); err != nil {
        log.Fatal(err)
    }
}