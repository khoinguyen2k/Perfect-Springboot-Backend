class GatherEmployeeInfo {
    constructor(jsonText) {
        let obj = JSON.parse(jsonText);
        this.fullName = obj.fullName;
        this.phoneNumber = obj.phoneNumber;
        this.gatherPointLeader = obj.gatherPointLeader;
    }

    showInfo() {
        let fullNameBox = document.getElementById("fullname");
        let addressBox = document.getElementById("address");
        let phoneNumberBox = document.getElementById("phone");

        fullNameBox.innerHTML = this.fullName;
        addressBox.innerHTML = this.gatherPointLeader.address;
        phoneNumberBox.innerHTML = this.phoneNumber;
    }
}

let gatherEmployeeUsername = sessionStorage.getItem("gatherEmployeeUsername");

document.getElementById("show-info").addEventListener("click", function (event) {
    event.preventDefault();

    let url = "http://localhost:8080/employee/gather/information";

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: gatherEmployeeUsername
    };

    if (gatherEmployeeUsername !== "") {
        fetch(url, options)
            .then(response => response.text())
            .then(data => {
                let employee = new GatherEmployeeInfo(data);
                employee.showInfo();

            })
            .catch(error => {
                console.error("Error:", error);
            });
    }

});

