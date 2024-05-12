
const jwt = getCookie('jwt'); // Замените на имя вашей куки с JWT токеном
function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}
fetch("/auth/get/user", {
    method: 'GET',

})
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log(data);
        displayUser(data);
    })
    .catch(error => {
        console.error('Ошибка при выполнении запроса:', error);
    });


fetch("/order/user/showall", {
    method: 'GET',
    headers: {
        Authorization: "Bearer " + jwt,
        Cookie: document.cookie,
    },
})
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log(data);
        displayOrderItem(data);
    })
    .catch(error => {
        console.error('Ошибка при выполнении запроса:', error);
    });


function displayUser(userInfo) {
    let nameContainer = document.getElementById("name-container");

    let name = document.createElement("p");
    name.textContent = userInfo.username;
    name.id = "name";

    nameContainer.appendChild(name);
}



function displayOrderItem(orderItems) {
    let orderContainer = document.getElementById("order");
    orderContainer.id = "order";
    let imgOrder = document.createElement("img-order");
    imgOrder.id = "img-order";
    orderContainer.appendChild(imgOrder);
    orderItems.forEach((item) => {
        let imgOrderCont = document.createElement("div");
        imgOrderCont.classList.add("img-order-cont");

        let img = document.createElement("img");
        img.src = "/img/cl.jpg";

        let text = document.createElement("p");
        text.textContent = "id: "+item.id

        let code = document.createElement("p");
        code.textContent = "Код: "+item.code

        imgOrderCont.appendChild(text);
        imgOrderCont.appendChild(code);
        imgOrder.appendChild(imgOrderCont);
    });
}
