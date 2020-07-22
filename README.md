# cosc211-final-project
About: My project is a text analysis program that takes a body of text and outputs information about that text. This information includes common phrases and a probabilistic sentence prediction.
Data: The data for this project is drawn from a body of text, read in from a file. The text is stored in the programs as arrays of Strings.
Data Structures: The main data structure for this program is a graph. Words and punctuation are vertices, and edges describe the order of these, i.e. an edge from “alphabet” to “zoo” indicates that the phrase “alphabet zoo” appears in the text. Vertices and edges also contain information on how many times a given word or edge appears.
Instructions: The programs can be run simply by running the main method of each “FP” class, in which case they use the “text1.txt” file provided. They can also be run on a file specified from the first argument of the command line. A “text2.txt” is also provided and can be used either by this command line instruction or by editing the code directly where the “filename” variable is initialized.
Future: I would like to continue this project by implementing higher-order text predictions by using Markov matrices. I would also like to learn more about how linguistics can be studied through computation beyond word sequences, e.g. sentence structures, dialects, etc. to implement these analyses in the project.
Acknowledgements: 
“Java Platform, Standard Edition 6 API Specification” by Oracle. https://docs.oracle.com/javase/6/docs/api/
“Gettysburg Address” by Abraham Lincoln. https://en.wikipedia.org/wiki/Gettysburg_Address
