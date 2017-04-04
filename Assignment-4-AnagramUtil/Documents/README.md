Nickolas Komarnitsky
u0717854
02/09/2017
2420
Assignment04
Elijah Yarisantos
I pledge that the work done here was my own and that I have learned how to write this program, such that I could throw it out and restart and finish it in a timely manner. I am not turning in any work that I cannot understand, describe, or recreate. I further acknowledge that I contributed substantially to all code handed in and vouch for it's authenticity.
Nickolas Komarnitsky

I went a little bit further with this assignment than my partner, creating a hashmap version of the getLargestAnagrams method. I knew that the insertion sort was not the best and I wanted to figure out what was and a hashmap seemed like it would be. This code has three methods that my partners doesn't: getKey(String string), getLargestAnagramGroupHashMap(String filename) and getLargetAnagramsHashMap(String[] anagramsList). I also went and got two more word lists to test, with all_words being almost 375K words in order to check the hashmap method. It finished the words_english almost instantly where as the insertion sort one took a while for a list of that size. all_words took a little bit, but stil less that a second to find the largest group.