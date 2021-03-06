package idgeneration;

/**
 * Implementation of LongIdToStringConverter, based on conversion from decimal numeral system to base-n numeral system.
 * All identifiers will have fixed size.
 */
public class AlphabetBasedLongIdToStringConverterImpl implements LongIdToStringConverter {
    /**
     * @param alphabet symbols for new number representation. Count of symbols in alphabet will be used as a base of
     *                 new numeral system.
     * @param idSize required size of identifier. If after conversion obtained id which has length less than
     *               required, it will be padded with the first symbol in alphabet to fit required size.
     */
    public AlphabetBasedLongIdToStringConverterImpl(Alphabet alphabet, int idSize) {
        this.alphabet = alphabet;
        this.idSize = idSize;
        CalculateBaseDegrees();
        CalculateMaxNumber();
    }

    @Override
    public String convert(long value) {
        CheckIdOnPreconditions(value);
        int index = idSize - 1;
        char[] charArray = new char[idSize];
        long currentNumber = value;

        while (index >= 0) {
            int remainder = (int)(currentNumber % alphabet.getCount());
            charArray[index--] = alphabet.getCorrespondingCharacter(remainder);
            currentNumber = currentNumber / alphabet.getCount();
        }

        return new String(charArray);
    }

    private void CheckIdOnPreconditions(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id should be greater than zero");
        }

        if (id > maxNumberThatCanBeConverted) {
            throw new IllegalArgumentException("In given numeral system id should be lower than " + maxNumberThatCanBeConverted);
        }
    }

    private void CalculateMaxNumber() {
        long maxNumber = 0;
        int maxCoefficient = (alphabet.getCount() - 1);
        for (int i = 0; i < idSize; i++) {
            maxNumber += maxCoefficient * baseDegrees[i];
        }

        maxNumberThatCanBeConverted = maxNumber;
    }

    private void  CalculateBaseDegrees() {
        baseDegrees = new long[idSize];
        baseDegrees[0] = 1;
        for (int i = 1; i < baseDegrees.length; i++) {
            baseDegrees[i] = baseDegrees[i - 1] * alphabet.getCount();
        }
    }

    private Alphabet alphabet;
    private int idSize;
    private long maxNumberThatCanBeConverted;
    private long[] baseDegrees;
}