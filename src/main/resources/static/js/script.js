document.addEventListener('DOMContentLoaded', () => {
    const carList = document.getElementById('cars');

    fetch('/cars')
        .then(response => response.json())
        .then(data => {
            const cars = data._embedded.cars; // Access the nested cars array
            cars.forEach(car => {
                const li = document.createElement('li');
                li.textContent = `${car.make} ${car.model} (${car.year}) - ${car.color}`;
                carList.appendChild(li);
            });
        });

    document.getElementById('login-form').addEventListener('submit', (e) => {
        e.preventDefault();
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        fetch('/users/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        })
        .then(response => {
            if (response.ok) {
                alert('Login successful');
            } else {
                alert('Login failed');
            }
        });
    });

    document.getElementById('signup-form').addEventListener('submit', (e) => {
        e.preventDefault();
        const username = document.getElementById('signup-username').value;
        const password = document.getElementById('signup-password').value;

        fetch('/users/signup', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        })
        .then(response => {
            if (response.ok) {
                alert('Signup successful');
            } else {
                alert('Signup failed');
            }
        });
    });
});
