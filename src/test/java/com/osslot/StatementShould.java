package com.osslot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatementShould {

    @Test
    void runsWithValidFileName() {
        Statement.main(new String[]{"src/test/resources/movies.tsv"});
    }

    @Test
    void throwsExceptionWhenNoArgument() {
        assertThrows(IllegalArgumentException.class, () -> Statement.main(new String[]{}), "Input file name should be set as first argument");
    }

    @Test
    void throwsExceptionWhenFirstArgIsNull() {
        assertThrows(IllegalArgumentException.class, () -> Statement.main(new String[]{ null }), "Input file name should be set as first argument");
    }

    @Test
    void throwsExceptionWhenMoreThan1Argument() {
        assertThrows(IllegalArgumentException.class, () -> Statement.main(new String[]{ "arg0", "arg1" }), "Input file name should be set as first argument");
    }

    @Test
    void throwsExceptionWhenInvalidInputFileName() {
        assertThrows(IllegalArgumentException.class, () -> Statement.main(new String[]{ "unexistingFile" }), "Input file unexistingFile does not exist");
    }

}