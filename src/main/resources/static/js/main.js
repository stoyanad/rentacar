document.addEventListener('DOMContentLoaded', () => {
    updateUI();
    loadCars();
});

function loadCars() {
    const carList = document.getElementById('carList');

    fetch('/cars')
        .then(response => response.json())
        .then(data => {
            const cars = data._embedded.cars; // Access the nested cars array
            cars.forEach(car => {
                const li = document.createElement('li');
                li.textContent = `${car.make} ${car.model} (${car.year}) - ${car.color} - ${car.availability ? 'Available' : 'Not Available'}`;
                carList.appendChild(li);
            });
        })
        .catch(error => {
            console.error('Error fetching cars:', error);
        });
}

function updateUI() {
    const userActions = document.getElementById('userActions');
    const signupButtonContainer = document.getElementById('signupButtonContainer');
    const token = localStorage.getItem('token');

    userActions.innerHTML = ''; // Clear the current content

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
            const greeting = document.createElement('span');
            greeting.textContent = 'Hello, ' + username;

            const logoutButton = document.createElement('button');
            logoutButton.textContent = 'Logout';
            logoutButton.id = 'logoutButton';
            logoutButton.addEventListener('click', logout);

            userActions.appendChild(greeting);
            userActions.appendChild(logoutButton);

            // Hide the signup button
            signupButtonContainer.style.display = 'none';
        })
        .catch(error => {
            // User not authenticated, show login button
            const loginButton = document.createElement('a');
            loginButton.textContent = 'Login';
            loginButton.id = 'loginButton';
            loginButton.href = '/login';

            userActions.appendChild(loginButton);

            // Show the signup button
            signupButtonContainer.style.display = 'block';
        });
    } else {
        // No token, show login button and signup button
        const loginButton = document.createElement('a');
        loginButton.textContent = 'Login';
        loginButton.id = 'loginButton';
        loginButton.href = '/login';

        userActions.appendChild(loginButton);

        signupButtonContainer.style.display = 'block';
    }
}

function logout() {
    // Clear the token from localStorage or sessionStorage
    localStorage.removeItem('token');
    // Redirect to login page upon successful logout
    window.location.href = '/';
}
