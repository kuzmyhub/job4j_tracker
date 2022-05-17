package ru.job4j.collection;

import org.junit.Test;
import java.util.Comparator;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.*;

public class JobTest {
    @Test
    public void whenComparatorByAscName() {
        Comparator<Job> cmpByAscName = new JobAscByName();
        int rsl = cmpByAscName.compare(
                new Job("Refactor method", 1),
                new Job("Write pattern", 0)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorByDescName() {
        Comparator<Job> cmpByDescName = new JobDescByName();
        int rsl = cmpByDescName.compare(
                new Job("Refactor method", 1),
                new Job("Write pattern", 0)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorByAscPriority() {
        Comparator<Job> cmpByAscPriority = new JobAscByPriority();
        int rsl = cmpByAscPriority.compare(
                new Job("Refactor method", 1),
                new Job("Write pattern", 0)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorByDescPriority() {
        Comparator<Job> cmpByDescPriority = new JobDescByPriority();
        int rsl = cmpByDescPriority.compare(
                new Job("Refactor method", 1),
                new Job("Write pattern", 0)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorByDescNameAndAscPriority() {
        Comparator<Job> cmpByDescNameAndAscPriority = new JobDescByName()
                .thenComparing(new JobAscByPriority());
        int rsl = cmpByDescNameAndAscPriority.compare(
                new Job("Do task", 0),
                new Job("Create task", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenComparatorByAscNameAndDescPriority() {
        Comparator<Job> cmpByAscNameAndDescPriority = new JobAscByName()
                .thenComparing(new JobDescByPriority());
        int rsl = cmpByAscNameAndDescPriority.compare(
                new Job("Do task", 0),
                new Job("Create task", 1)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenComparatorByNameAndPriority() {
        Comparator<Job> cmpNamePriority = new JobDescByName()
                .thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Impl task", 1)
        );
        assertThat(rsl, greaterThan(0));
    }
}