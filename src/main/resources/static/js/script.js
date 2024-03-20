function compareAvailabilities(userId, friendId) {
    fetch(`/compare-availabilities?userId1=${userId}&userId2=${friendId}`)
        .then(response => response.json())
        .then(data => {
            alert(`Common Availability: ${data}`);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}
