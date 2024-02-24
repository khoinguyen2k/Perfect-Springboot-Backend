class CustomerInfo {
    constructor(jsonText) {
        let obj = JSON.parse(jsonText);
        this.fullName = obj.fullName;
        this.address = obj.address;
        this.phoneNumber = obj.phoneNumber;
        this.postalCode = obj.postalCode;
    }

    showInfo() {
        let fullNameBox = document.getElementById('fullname');
        let addressBox = document.getElementById('address');
        let phoneNumberBox = document.getElementById('phone');
        let postalCodeBox = document.getElementById('postal-code');

        fullNameBox.innerHTML = this.fullName;
        addressBox.innerHTML = this.address;
        phoneNumberBox.innerHTML = this.phoneNumber;
        postalCodeBox.innerHTML = this.postalCode;
    }
}

document.getElementById("show-info").addEventListener("click", function (event) {
    event.preventDefault();

    let url = "http://localhost:8080/customer/information";
    let customerUsername = sessionStorage.getItem("customerUsername");

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: customerUsername
    };

    if (customerUsername !== "") {
        fetch(url, options)
            .then(response => response.text())
            .then(data => {
                let customer = new CustomerInfo(data);
                customer.showInfo("", "");

            })
            .catch(error => {
                console.error("Error:", error);
            });
    }

});

