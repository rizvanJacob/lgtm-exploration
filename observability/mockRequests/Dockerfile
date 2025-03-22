# Use an official Node.js image
FROM node:20

# Set the working directory
WORKDIR /app

# Copy package files first and install dependencies
COPY package*.json ./
RUN npm install

# Copy the rest of your app
COPY . .

# Command to run the index.ts file
CMD ["ts-node", "index.ts"]