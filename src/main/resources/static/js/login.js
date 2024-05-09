document
    .getElementById("usernameForm")
    .addEventListener("submit", function (event) {
        event.preventDefault();

        var username = document.getElementById("name").value;
        var password = document.getElementById("password").value;

        fetch("/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Cookie: document.cookie,
            },
            body: JSON.stringify({ username: username, password: password }),
        })
            .then((response) => response.json())
            .then((data) => {
                if (data.user == null) {
                    displayError();
                } else {
                    let jwt = data.jwt;
                    document.cookie = `jwt=${jwt}; path=/`;
                    goToShop(jwt);
                }
            })
            .catch((error) => console.error("Ошибка", error));
    });

function goToShop(jwt) {
    fetch("/main", {
        method: "GET",
        headers: {
            Authorization: "Bearer " + jwt,
            Cookie: document.cookie,
        },
    })
        .then((response) => {
            if (response.status === 200) {
                window.location.href = "/main";
            } else {
                console.error("Error", response.status);
            }
        })
        .catch((error) => console.error("Error", error));
}



function displayError() {
    if (document.getElementById("error") == null) {
        let form = document.getElementById("usernameForm");
        let error = document.createElement("p");
        error.id = "error";
        error.textContent = "Error!";
        form.appendChild(error);
    }
}

document
    .getElementById("enter-question")
    .addEventListener("click", function (event) {
        event.preventDefault();

        var xhr = new XMLHttpRequest();
        xhr.open("GET", "/auth/signup", true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onload = function () {
            if (xhr.status == 200) {
                window.location.href = "/auth/signup";
            } else {
                console.log("Error ", xhr.status);
            }
        };
        xhr.send();
    });