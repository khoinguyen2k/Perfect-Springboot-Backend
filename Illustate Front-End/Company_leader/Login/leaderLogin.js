document.getElementById("login-form").addEventListener("submit", function (event) {

    event.preventDefault();

    let username = document.getElementById("box-username").value;
    let password = document.getElementById("box-password").value;

    let inputData = {
        username: username,
        password: password
    };

    let url = "http://localhost:8080/admin/sign-in";

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(inputData)
    };

    fetch(url, options)
        .then(response => response.text())
        .then(data => {
            alert(data);
            if (data === SIGN_IN_SUCCESSFUL) {

                sessionStorage.setItem("companyLeaderUsername", username);
                window.location.href = "../Leader_page/companyLeader.html";
            }

        })
        .catch(error => {
            console.error("Error:", error);
        });

});