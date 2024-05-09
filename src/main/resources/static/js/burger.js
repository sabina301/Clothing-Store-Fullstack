const menuToggle = document.querySelector(".menu-toggle");
const menu = document.querySelector(".menu");

menuToggle.addEventListener("click", () => {
    menu.classList.toggle("show");

    document.querySelector(".line-1").classList.toggle("close");
    document.querySelector(".line-2").classList.toggle("close");
    document.querySelector(".line-3").classList.toggle("close");
});