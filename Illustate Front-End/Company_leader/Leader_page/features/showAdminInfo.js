document.getElementById("show-info").addEventListener("click", function (event) {
    event.preventDefault();

    let url = "http://localhost:8080/point-leader/gather/information";
    let companyLeaderUsername = sessionStorage.getItem("companyLeaderUsername");

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: companyLeaderUsername
    };

    if (companyLeaderUsername !== "") {
        fetch(url, options)
            .then(response => response.text())
            .then(data => {
                let leader = new CompanyLeaderInfo(data);
                leader.showInfo();

            })
            .catch(error => {
                console.error("Error:", error);
            });
    }

});

