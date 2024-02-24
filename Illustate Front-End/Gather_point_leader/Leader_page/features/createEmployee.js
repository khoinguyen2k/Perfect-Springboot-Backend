document.getElementById("create-gather-employee-form").addEventListener("submit", function (event) {
    event.preventDefault();

    let formData = new FormData(this);
    let signUpForm = new GatherEmployeeSignUp(formData);

    let url = "http://localhost:8080/employee/gather/sign-up";

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