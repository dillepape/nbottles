public class NBottlesOfBeer {

    public static void main(String[] args) {
        int numberOfBottles = 99;
        int numberOfTimesToSing = 1;
        long singingSpeed = 250; // TODO is this a good default number?
        String bottlesArg = "--bottles=",
               singTimesArg = "--repetitions=",
               singSpeedArg = "--speed=";

        for (String arg : args) {
//        for (byte i = 0; i < args.length; i++) {
//            String arg = args[i];

            if (arg.startsWith(bottlesArg)) {
                numberOfBottles = Integer.parseInt(
                                  arg.substring(bottlesArg.length()));
            } else if (arg.startsWith(singTimesArg)) {
                numberOfTimesToSing = Integer.parseInt(
                                      arg.substring(singTimesArg.length()));
            } else if (arg.startsWith(singSpeedArg)) { // TODO parse =MILLI+NANO?
                singingSpeed = Long.parseLong(
                               arg.substring(singSpeedArg.length()));
            }
        }

        // TODO superfluous? if-else instead? try to implement with neither!
        switch (numberOfTimesToSing) {
            case 0:
                while (true) {
                    singSong(numberOfBottles, singingSpeed);
                }
            default:
                for (int i = 0; i < numberOfTimesToSing; i++) {
                    singSong(numberOfBottles, singingSpeed);
                }
        }
    }

    private static void singSong(int numberOfBottles, long singingSpeed) {
        for (int i = numberOfBottles; i >= 0; i--) {
            singVerse(verse(i), singingSpeed);
        }
    }

    private static String verse(int i) {
        String ofBeer = " of beer",
               iBottlesOfBeer = ((i != 0) ? i : "No more")
                                + " bottle" + ((i != 1) ? 's' : "") + ofBeer,
               iMinusOneBottlesOfBeer = ((i != 1) ? (i - 1) : "no more")
                                        + " bottle" + ((i != 2) ? 's' : "")
                                        + ofBeer,
               onTheWall = " on the wall",
               takeOneDown = "Take one down and pass it around, ",
               goToStore = "Go to the store and buy some more, 99 bottles";

        return iBottlesOfBeer + onTheWall + ", "
               + ((i != 0)
                 ? iBottlesOfBeer
                 : iBottlesOfBeer.toLowerCase()) + ".  \n" // TWO spaces
               + ((i != 0)
                 ? takeOneDown + iMinusOneBottlesOfBeer + onTheWall
                 : goToStore + ofBeer + onTheWall) + ". \n"; // ONE space
    }

    private static void singVerse(String verseLyrics, long singingSpeed) {
        // TODO verseLyrics.split("\n") then split those by split(" ")?
        String[] words = verseLyrics.split(" ");

        for (int i = 0; i < words.length; i++) {
            // XXX outputs a blank line after the "no more bottles" verse
            // XXX also, pause for one speed unit after first line of verse
            if (i < (words.length - 1)) {
                System.out.print(words[i] + " ");
            } else if (i == (words.length - 1)) {
                System.out.print(words[i]);
            } else {
                System.out.println();
            }

//            if (words[i].endsWith(".")) {
//                System.out.println(words[i]);
//            } else {
//                System.out.print(words[i] + " ");
//            }

            try {
                Thread.sleep(singingSpeed);
            } catch (InterruptedException iEx) {
                System.err.println(iEx);
            }
        }
    }
}

