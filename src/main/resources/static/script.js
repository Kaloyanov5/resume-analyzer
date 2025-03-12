const BASE_URL = "http://localhost:8080";

const resultDiv = document.getElementById("result");
const resumeForm = document.getElementById("resume-form");

resumeForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    const email = document.getElementById("email").value;
    const fileInput = document.getElementById("fileInput");

    if (!email || fileInput.files.length === 0) {
        alert("Please enter your email and upload a file.");
        return;
    }

    const formData = new FormData();
    formData.append("file", fileInput.files[0]);
    formData.append("email", email);

    loading(true);
    try {
        const response = await fetch(`${BASE_URL}/api/resume`, {
            method: "POST",
            body: formData
        });
        loading(false);

        if (response.ok) {
            resultDiv.innerText = "Check your results on your email!";
        } else if (response.status === 400 || response.status === 500 || response.status === 204) {
            alert(await response.text());
        } else {
            alert("Error analyzing resume.");
        }
    } catch (error) {
        alert("Error occurred! Check console for more information.");
        console.error("Error: ", error);
    }
})

const loading = (bool) => {
    resultDiv.innerText = bool ? "Loading..." : "";
}