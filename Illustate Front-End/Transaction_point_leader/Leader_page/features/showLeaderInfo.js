document.getElementById("show-info").addEventListener("click", function (event) {
    event.preventDefault();

    let url = "http://localhost:8080/point-leader/gather/information";
    let gatherLeaderUsername = sessionStorage.getItem("transactionLeaderUsername");

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: gatherLeaderUsername
    };

    if (gatherLeaderUsername !== "") {
        fetch(url, options)
            .then(response => response.text())
            .then(data => {
                let leader = new GatherLeaderInfo(data);
                leader.showInfo();

            })
            .catch(error => {
                console.error("Error:", error);
            });
    }

});

