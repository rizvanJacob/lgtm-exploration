import axios from "axios";

const host = "spring-micrometer-app";
const port = "8080";

const axiosInstance = axios.create({
  baseURL: `http://${host}:${port}`,
});

type Method = "GET" | "POST" | "PUT" | "DELETE";

// Define your requests
const requests: {
  endpoint: string;
  method: Method;
}[] = [

];

const setupByIdRequests = () => {
  for (let i = 0; i < 1000; i++) {
    requests.push({ endpoint: "/wait", method: "GET" }),
    requests.push({ endpoint: "/wait", method: "POST" }),

    requests.push({ endpoint: "/wait/" + i, method: "GET" });
    requests.push({ endpoint: "/wait/" + i, method: "PUT" });
    requests.push({ endpoint: "/wait/" + i, method: "DELETE" });
  }
};

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

setupByIdRequests("GET");
setupByIdRequests("PUT");
setupByIdRequests("DELETE");

// Run the request every 5 seconds
setInterval(performRequest, 50);

// Initial trigger
performRequest();
