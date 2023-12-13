<h1 align="center" id="title" >Bamboo</h1>

<p align="center" ><img src="https://i.imgur.com/bzUB1sS.jpeg" alt="project-image"></p>

<p id="description" align="centre" style="text-align: center">Bamboo is responsible for connecting the Crowdeo Dashboard with the 'Panda' device. The API facilitates seamless data transmission between the crowd tracking system and the dashboard, enabling real-time visualization and analysis of crowd movement data.</p>

<p align="center">
<img src="https://github.com/Olszewski-Jakub/Bamboo/actions/workflows/develop.yml/badge.svg?branch=develop" alt="shields">
&nbsp;&nbsp;
<img src="https://github.com/Olszewski-Jakub/Bamboo/actions/workflows/main.yml/badge.svg?branch=main" alt="shields">
&nbsp;&nbsp;
<img src="https://wakatime.com/badge/user/018bc9f6-6b41-4f92-bfd1-4ae3d4c681b1/project/018bc9f9-a4f9-4332-b0bc-5f57cd0509f6.svg" alt="shields">
</p>


<h2>🧐 Features</h2>

Here're some of the project's best features:

* Run in docker container
* User/Device Authorisation
* CI/CD pipelines
* Documentation made using Swagger
* API-Key generation
* Generating configuration for devices
* Managing devices
* Collecting data from Panda Devices
* Analysing collected data to present it in a user-friendly format

<h2>🛠️ Installation Steps:</h2>

1. Cloning project

```
git clone https://github.com/Olszewski-Jakub/Bamboo.git
```

2. [Download Docker](https"//www.docker.com/products/docker-desktop)
3. [Firebase configuration](https://firebase.google.com/)
    1. Create Firebase project
    2. Turn on Firebase Authentication via **Email/Password**
    3. Go to project settings
    4. Then go to Service Accounts and generate new **Private Key**
4. Copy private key to src/main/resources and rename it to **firebase_config.json**
5. Start project
 ```
 docker-compose up
 ```

6.Documentation will be available at

```
http://localhost:8080/swagger-ui/index.html#/
```



<h2>💻 Built with</h2>

Technologies used in the project:
<p align="center">
<img src="https://camo.githubusercontent.com/57cec1c01287dfdc2a3fe64954936293c761b7fa9a7fc1b9de3916a295f15170/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6a6176612d2532334544384230302e7376673f7374796c653d666f722d7468652d6261646765266c6f676f3d6f70656e6a646b266c6f676f436f6c6f723d7768697465" alt="shields">
<img src="https://camo.githubusercontent.com/49f645b5e439b0d748424412207eae5748b81d77563f866d8528f60c66b669e1/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f737072696e672d2532333644423333462e7376673f7374796c653d666f722d7468652d6261646765266c6f676f3d737072696e67266c6f676f436f6c6f723d7768697465" alt="shields">
<img src="https://camo.githubusercontent.com/29e7fc6c62f61f432d3852fbfa4190ff07f397ca3bde27a8196bcd5beae3ff77/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f706f7374677265732d2532333331363139322e7376673f7374796c653d666f722d7468652d6261646765266c6f676f3d706f737467726573716c266c6f676f436f6c6f723d7768697465" alt="shields">
<img src="https://camo.githubusercontent.com/6b7f701cf0bea42833751b754688f1a27b6090fdf90bf2b226addff01be817f0/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f646f636b65722d2532333064623765642e7376673f7374796c653d666f722d7468652d6261646765266c6f676f3d646f636b6572266c6f676f436f6c6f723d7768697465" alt="shields">
<img src="https://camo.githubusercontent.com/28577ff4dc7abd641b91f419821ba341bc1ad5037e5dfff20f9209a7f5465759/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f2d537761676765722d253233436c6f6a7572653f7374796c653d666f722d7468652d6261646765266c6f676f3d73776167676572266c6f676f436f6c6f723d7768697465" alt="shields">
<img src="https://camo.githubusercontent.com/a65fcdf7030d79c00f4c3d8bab84de39107f5777fca4d12f0cb64440015183fe/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f66697265626173652d2532333033394245352e7376673f7374796c653d666f722d7468652d6261646765266c6f676f3d6669726562617365" alt="shields">
<img src="https://camo.githubusercontent.com/071595b0fe0ac08046e2eddca8c6f64ae763a9380fea3df7e1aa174685a61a92/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f477261646c652d3032333033412e7376673f7374796c653d666f722d7468652d6261646765266c6f676f3d477261646c65266c6f676f436f6c6f723d7768697465" alt="shields">
</p>

<h2>🍰 Contribution Guidelines:</h2>

<h3>Getting Started:</h2>

1. Fork the Repository:
   - Fork the repository on GitHub.
2. Clone the Repository:
   - Clone your forked repository to your local machine.

```
git clone https://github.com/your-username/repository.git
```

3. Branching:
   - Create a new branch for your contribution.

```
  git checkout -b feature-branch
```

<h3>Making Changes:</h3>
**1. Code Standards:**

- Adhere to Java and Spring coding standards.
   - Use camelCase for variable and method names.
   - Follow REST API best practices.
   - Keep code modular and well-organized.

**2. Documentation:**

- Provide clear and concise documentation for any new features or changes.
- Update existing documentation as needed.

3. Testing:
   - Unit Tests:
      - Write unit tests for new functionalities.
      - Ensure existing tests pass with your changes.

<h3>Submission:</h3>
1. Committing:

   - Make meaningful and well-commented commits.
   - Follow the [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) specification if possible.

2. Pull Request:
   - Open a pull request against the develop branch.
   - Include a clear and informative title and description.

3. Collaboration:
- Be responsive to feedback and address comments promptly.
- Make any necessary adjustments as requested.

<h2>💖Like my work?</h2>

- discord: olszewski\_ 
- email: j.olszewski05@gmail.com