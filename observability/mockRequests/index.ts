import axios, { Method } from 'axios';

const host = "spring-micrometer-app"
const port = "8080"

// Define your requests
const requests: { url: string; method: Method }[] = [
  { endpoint: '/wait/0', method: 'GET' },
  { endpoint: '/wait/1', method: 'GET' },
  { endpoint: '/wait/2', method: 'GET' },
];

// Function to select a random request
function getRandomRequest() {
  const index = Math.floor(Math.random() * requests.length);
  return requests[index];
}

// Function to perform the request
async function performRequest() {
    //randomly skip requests
    if (Math.random() < 0.5) {
        return;
        }

  const { url, method } = getRandomRequest();

    axios({
      method,
      url,
      // Provide dummy data for POST/PUT
      data: method === 'POST' || method === 'PUT' ? { title: 'foo', body: 'bar', userId: 1 } : undefined,
    });
}

// Run the request every 5 seconds
setInterval(performRequest, 50);

// Initial trigger
performRequest();