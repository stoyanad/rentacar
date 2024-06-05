document.addEventListener('DOMContentLoaded', function () {
    updateUI();
});

function updateUI() {
    const userActions = document.getElementById('userActions');
    const token = localStorage.getItem('token');

    if (token) {
        fetch('/users/current-user', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            },
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('User not authenticated');
            }
        })
        .then(user => {
            // User authenticated, update UI
            const username = user.username;
            const logoutButton = document.createElement('button');
            logoutButton.textContent = 'Logout';
            logoutButton.addEventListener('click', logout);

            const greeting = document.createElement('div');
            greeting.textContent = 'Hello, ' + username + '!';
            greeting.appendChild(logoutButton);

            userActions.appendChild(greeting);
        })
        .catch(error => {
            // User not authenticated, show login button
            const loginButton = document.createElement('a');
            loginButton.textContent = 'Login';
            loginButton.href = '/login';

            userActions.appendChild(loginButton);
        });
    } else {
        // No token, show login button
        const loginButton = document.createElement('a');
        loginButton.textContent = 'Login';
        loginButton.href = '/login';

        userActions.appendChild(loginButton);
    }
}

function logout() {
    // Clear the token from localStorage or sessionStorage
    localStorage.removeItem('token');
    // Redirect to login page upon successful logout
    window.location.href = '/login';
}
