document.addEventListener('DOMContentLoaded', function () {
    const signupForm = document.getElementById('signupForm');
    const errorMessageElement = document.getElementById('errorMessage');

    signupForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const formData = new FormData(signupForm);
        const username = formData.get('username');
        const password = formData.get('password');
        const firstName = formData.get('firstName');
        const lastName = formData.get('lastName');

        // Send sign-up request to server
        fetch('/users/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: username,
                password: password,
                firstName: firstName,
                lastName: lastName
            })
        })
        .then(response => {
            if (response.ok) {
                // Redirect to login page upon successful sign-up
                window.location.href = '/login';
            } else {
                return response.json(); // Extract JSON response body
            }
        })
        .then(data => {
            // Check if the response contains an error message
            if (data && data.error) {
                // Display the error message
                errorMessageElement.textContent = data.error;
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
    });
});
