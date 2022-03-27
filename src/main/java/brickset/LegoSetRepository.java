package brickset;

import repository.Repository;
import java.util.Comparator;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }



    /**
     * Returns the number of LEGO sets with the tag specified.
     *
     * @param tag a LEGO set tag
     * @return the number of LEGO sets with the tag specified
     */
    public long countLegoSetsWithTag(String tag) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getTags() != null && legoSet.getTags().contains(tag))
                .count();
    }

    /**
     * Prints out the name of the Lego sets in order
     */
    public void printOrderedLegoSets() {
        getAll().stream()
                .map(LegoSet::getName)
                .sorted()
                .forEach(System.out::println);
    }

    /**
     * Prints out the name of the Lego sets over a minimum number of pieces
     *
     * @param minimum a number to give a minimum for Lego set pieces
     */
    public void printLegoSetsOverGivenPieces(int minimum) {
        getAll().stream()
                .filter(legoSet -> legoSet.getPieces() > minimum && legoSet.getPieces() != 0)
                .map(LegoSet::getName)
                .sorted()
                .forEach(System.out::println);
    }

    /**
     * Prints out the packaging types of the Lego sets
     */
    public void printLegoSetPackaging() {
        getAll().stream()
                .map(LegoSet::getPackagingType)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * Returns the number of pieces of the given theme
     *
     * @param theme a Lego Set theme
     * @return sum of pieces with the specified theme
     */
    public long sumLegoPiecesByThemes(String theme) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getTheme().equals(theme))
                .mapToLong(LegoSet::getPieces)
                .sum();
    }

    /**
     * Function for returning all the data of the smallest Lego set in the whole collection
     *
     * @return all the data of the smallest Lego Set in the whole collection
     */
    public brickset.LegoSet TheSmallestLegoSet() {
        return getAll().stream()
                .min(Comparator.comparingLong(LegoSet::getPieces))
                .get();
    }

    public static void main(String[] args) {
        var repository = new LegoSetRepository();
        System.out.println(repository.countLegoSetsWithTag("Microscale"));

        System.out.println();

        //First Method
        System.out.println("Lego set names in alphabetical order:");
        repository.printOrderedLegoSets();

        System.out.println();

        //Second Method
        System.out.println("Lego sets over 600 pieces");
        repository.printLegoSetsOverGivenPieces(600);

        System.out.println();

        //Third Method
        System.out.println("Packaging types of the Lego sets: ");
        repository.printLegoSetPackaging();

        System.out.println();

        //Fourth Method
        String theme = "Games";
        System.out.println("Theme: " + theme + "Number of pieces: " + repository.sumLegoPiecesByThemes(theme));

        System.out.println();

        //Fifth Method
        System.out.println("The smallest lego set: " + repository.TheSmallestLegoSet());
    }

}
