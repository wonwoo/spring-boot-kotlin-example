package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime


internal class ExtensionsKtTests {


    @Test
    fun `date just now test`() {

        val now = LocalDateTime.of(2019, 5, 6, 12, 12, 3)

        val dateFormat = LocalDateTime.of(2019, 5, 6, 12, 12, 0)
            .formatDateAgo(now)

        assertThat(dateFormat).isEqualTo("just now")


    }


    @Test
    fun `date seconds ago test`() {

        val now = LocalDateTime.of(2019, 5, 6, 12, 12, 20)

        val dateFormat = LocalDateTime.of(2019, 5, 6, 12, 12, 0)
            .formatDateAgo(now)

        assertThat(dateFormat).isEqualTo("20 seconds ago")


    }


    @Test
    fun `date minutes ago test`() {

        val now = LocalDateTime.of(2019, 5, 6, 12, 12)

        val dateFormat = LocalDateTime.of(2019, 5, 6, 12, 11)
            .formatDateAgo(now)

        assertThat(dateFormat).isEqualTo("1 minutes ago")


    }


    @Test
    fun `date hours ago test`() {

        val now = LocalDateTime.of(2019, 5, 6, 12, 12)

        val dateFormat = LocalDateTime.of(2019, 5, 6, 11, 12)
            .formatDateAgo(now)

        assertThat(dateFormat).isEqualTo("1 hours ago")


    }

    @Test
    fun `date days ago test`() {

        val now = LocalDateTime.of(2019, 5, 6, 12, 12)

        val dateFormat = LocalDateTime.of(2019, 5, 4, 12, 12)
            .formatDateAgo(now)

        assertThat(dateFormat).isEqualTo("2 days ago")


    }

    @Test
    fun `date months ago test`() {

        val now = LocalDateTime.of(2019, 5, 6, 12, 12)

        val dateFormat = LocalDateTime.of(2019, 3, 4, 12, 10)
            .formatDateAgo(now)

        assertThat(dateFormat).isEqualTo("2 months ago")


    }

    @Test
    fun `date years ago test`() {

        val now = LocalDateTime.of(2019, 5, 6, 12, 12)

        val dateFormat = LocalDateTime.of(2018, 4, 6, 12, 12)
            .formatDateAgo(now)

        assertThat(dateFormat).isEqualTo("1 years ago")


    }

}