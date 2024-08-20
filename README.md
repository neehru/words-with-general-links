<h1>Words With General Links </h1>

<h2>About The Project</h2>

This program was created in Java using concepts I learnt throughout my first year of university. It is similar to the New York Time's game called "Connections" where the player is given 16 words and must group them into 4 groups of 4, without knowing what the words have in common. Creating this project improved my confidence using Swing and AWT APT to create GUIs and bettered my understanding of data structures like HashMaps.<br/>

<h2>Getting Started</h2>

<h3>Installation</h3>

1. Open the terminal and locate the directory you want to store the game in
2. Clone the repo
   ```sh
   git clone https://github.com/neehru/words-with-general-links
   ```
3. Enter the new folder
   ```sh
   cd words-with-general-links
   ```
4. Compile the main class
   ```js
   javac src/code/WordsWithGeneralLinks.java
   ```
5. Run the main class
   ```sh
   java src.code.WordsWithGeneralLinks
   ```

<h3>Customising Game Words</h3>
Locate src/resources/links.txt and add to the file using the format:<br/>
<ul>
  <li>link, word 1, word 2, word 3, word 4</li>
</ul>
Make sure each link and its corresponding words take up one line. If a line contains more than 5 words or phrases separated by commas that line will be ignored. If there are less than 4 valid links with corresponding words an error message will appear and the game will be unable to run.<br/>

<h2>Contact</h2>
Neehru Tumber - neehru25.tumber@gmail.com<br/>
Project Link - https://github.com/neehru/words-with-general-links
