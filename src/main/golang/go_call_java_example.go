package main

import (
    "os/exec"
)
string data_path = "/root/user_data1.csv"
string jar_path = "p4p.jar"
func main() {
    cmd := exec.Command("java", "-jar",jar_path, data_path)
    if err := cmd.Run(); err != nil {
        log.Fatal(err)
    }
}