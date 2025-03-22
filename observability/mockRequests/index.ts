import axios from "axios";

const host = "spring-micrometer-app";
const port = "8080";

const axiosInstance = axios.create({
  baseURL: `http://${host}:${port}`,
});

// Define your requests
const requests: {
  endpoint: string;
  method: "GET" | "POST" | "PUT" | "DELETE";
}[] = [
  { endpoint: "/wait/0", method: "GET" },
  { endpoint: "/wait/1", method: "GET" },
  { endpoint: "/wait/2", method: "GET" },
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

  const { endpoint, method } = getRandomRequest();
  console.log(`Executing ${method} to ${endpoint}`);

  axiosInstance
    .request({
      method,
      url: endpoint,
      // Provide dummy data for POST/PUT
      data:
        method === "POST" || method === "PUT"
          ? { title: "foo", body: "bar", userId: 1 }
          : undefined,
    })
    .catch((_exception) => {
      console.log(`${method} to ${endpoint} failed`);
    });
}

// Run the request every 5 seconds
setInterval(performRequest, 50);

// Initial trigger
performRequest();
