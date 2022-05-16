import (
    "os/exec"
    "fmt"
)
var data_path = "/root/user_data1.csv"
var jar_path = "/root/P4P/P4P.jar"

func main() {
    cmd := exec.Command("java", "-jar",jar_path, "sg")
    stdout, err := cmd.Output()
    if err != nil {
        fmt.Println(err.Error())
        return
    }

    // Print the output
    fmt.Println(string(stdout))
}