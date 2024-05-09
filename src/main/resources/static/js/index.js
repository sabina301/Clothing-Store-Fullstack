
function getTokenFromCookie() {
    const cookies = document.cookie.split(';');
    for (let cookie of cookies) {
        const [name, value] = cookie.split('=');
        if (name.trim() === 'jwt') {
            return value;
        }
    }
    return null; // Токен не найден
}

document.getElementById("navbar").addEventListener("click", function (event) {
    if (event.target.classList.contains("navbar-link")) {
        event.preventDefault();

        const targetId = event.target.id;

        // Формируем URL в зависимости от id элемента навигации
        let url;
        if (targetId === "woman") {
            url = "/catalog/woman";
        } else if (targetId === "man") {
            url = "/catalog/man";
        } else if (targetId === "cart"){
            url = "/cart";
        } else if (targetId === "profile"){
            url = "/profile"
        }

        goToPage(url);
    }
});

function goToPage(url) {
    jwt = getTokenFromCookie()
    fetch(url, {
        method: "GET",
        headers: {
            Authorization: "Bearer " + jwt,
            Cookie: document.cookie,
        },
    })
        .then((response) => {
            if (response.status === 200) {
                window.location.href = url;
            } else {
                console.error("Error", response.status);
            }
        })
        .catch((error) => console.error("Error", error));
}
