/**
 * Handle create gather leader
 */
document.getElementById("create-gather-leader-form").addEventListener("submit", function (event) {
    event.preventDefault();

    let formData = new FormData(this);
    let companyLeaderUsername = sessionStorage.getItem('companyLeaderUsername');

    let signUpForm = new GatherLeaderSignUp(formData, companyLeaderUsername);

    let url = "http://localhost:8080/point-leader/gather/sign-up";

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(signUpForm)
    };

    fetch(url, options)
        .then(response => response.text())
        .then(data => {
            alert(data);
        })
        .catch(error => {
            console.error("Error:", error);
        });
});

/**
 * Handle create transaction leader
 */
document.getElementById("create-transaction-leader-form").addEventListener("submit", function (event) {
    event.preventDefault();

    let formData = new FormData(this);

    let signUpForm = new TransactionLeaderSignUp(formData);

    let url = "http://localhost:8080/point-leader/transaction/sign-up";

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(signUpForm)
    };

    fetch(url, options)
        .then(response => response.text())
        .then(data => {
            alert(data);
        })
        .catch(error => {
            console.error("Error:", error);
        });
});