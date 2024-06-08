package com.renhard.layarkaca.utils

import com.renhard.layarkaca.module.detail.model.DetailFilmResponse
import com.renhard.layarkaca.module.detail.model.ErrorResult
import com.renhard.layarkaca.module.detail.model.FilmGenre
import com.renhard.layarkaca.module.movies.model.MovieResult
import com.renhard.layarkaca.module.tvshows.model.TVShowResult
import com.renhard.layarkaca.repository.local.MovieEntity
import com.renhard.layarkaca.repository.local.TVShowEntity


object DataDummy {

    fun generateDummyMovies(): ArrayList<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(1,
            "Tom Clancy's Without Remorse",
                "29 Apr 2021",
            7.3,
            "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rEm96ib0sPiZBADNKBHKBv5bve9.jpg"))

        movies.add(MovieEntity(2,
            "Mortal Kombat",
            "07 Apr 2021",
            7.8,
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg"))

        movies.add(MovieEntity(3,
            "Vanquish",
            "16 Apr 2021",
            6.4,
            "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/AoWY1gkcNzabh229Icboa1Ff0BM.jpg"))

        movies.add(MovieEntity(4,
            "Godzilla vs. Kong",
            "24 Mar 2021",
            8.1,
            "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg"))

        movies.add(MovieEntity(5,
            "Nobody",
            "26 Mar 2021",
            8.4,
            "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg"))

        movies.add(MovieEntity(6,
            "Sentinelle",
            "05 Mar 2021",
            5.9,
            "Transferred home after a traumatizing combat mission, a highly trained French soldier uses her lethal skills to hunt down the man who hurt her sister.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/AmUGn1rJ9XDDP6DYn9OA2uV8MIg.jpg"))

        movies.add(MovieEntity(7,
            "Thunder Force",
            "09 Apr 2021",
            5.8,
            "In a world where supervillains are commonplace, two estranged childhood best friends reunite after one devises a treatment that gives them powers to protect their city.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/duK11VQd4UPDa7UJrgrGx90xJOx.jpg"))

        movies.add(MovieEntity(9,
            "Cherry",
            "26 Feb 2021",
            7.5,
            "Cherry drifts from college dropout to army medic in Iraq - anchored only by his true love, Emily. But after returning from the war with PTSD, his life spirals into drugs and crime as he struggles to find his place in the world.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pwDvkDyaHEU9V7cApQhbcSJMG1w.jpg"))

        movies.add(MovieEntity(9,
            "Zack Snyder's Justice League",
            "18 Mar 2021",
            8.5,
            "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg"))

        movies.add(MovieEntity(10,
            "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
            "16 Oct 2020",
            8.4,
            "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg"))

        return movies
    }

    fun generateRemoteDummyMovies(): ArrayList<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(1,
                "Tom Clancy's Without Remorse",
                "29 Apr 2021",
                7.3,
                "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rEm96ib0sPiZBADNKBHKBv5bve9.jpg"))

        movies.add(MovieEntity(2,
            "Mortal Kombat",
            "07 Apr 2021",
            7.8,
            "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg"))

        movies.add(MovieEntity(3,
            "Vanquish",
            "16 Apr 2021",
            6.4,
            "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/AoWY1gkcNzabh229Icboa1Ff0BM.jpg"))

        movies.add(MovieEntity(4,
            "Godzilla vs. Kong",
            "24 Mar 2021",
            8.1,
            "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg"))

        movies.add(MovieEntity(5,
            "Nobody",
            "26 Mar 2021",
            8.4,
            "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg"))

        movies.add(MovieEntity(6,
            "Sentinelle",
            "05 Mar 2021",
            5.9,
            "Transferred home after a traumatizing combat mission, a highly trained French soldier uses her lethal skills to hunt down the man who hurt her sister.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/AmUGn1rJ9XDDP6DYn9OA2uV8MIg.jpg"))

        movies.add(MovieEntity(7,
            "Thunder Force",
            "09 Apr 2021",
            5.8,
            "In a world where supervillains are commonplace, two estranged childhood best friends reunite after one devises a treatment that gives them powers to protect their city.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/duK11VQd4UPDa7UJrgrGx90xJOx.jpg"))

        movies.add(MovieEntity(9,
            "Cherry",
            "26 Feb 2021",
            7.5,
            "Cherry drifts from college dropout to army medic in Iraq - anchored only by his true love, Emily. But after returning from the war with PTSD, his life spirals into drugs and crime as he struggles to find his place in the world.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pwDvkDyaHEU9V7cApQhbcSJMG1w.jpg"))

        movies.add(MovieEntity(9,
            "Zack Snyder's Justice League",
            "18 Mar 2021",
            8.5,
            "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg"))

        movies.add(MovieEntity(10,
            "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
            "16 Oct 2020",
            8.4,
            "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg"))

        return movies
    }

    fun generateDummyTVShow(): ArrayList<TVShowEntity> {
        val tvShows = ArrayList<TVShowEntity>()

        tvShows.add(
            TVShowEntity(11,
            "The Falcon and the Winter Soldier",
            "19 Mar 2021",
            7.9,
            "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg"))

        tvShows.add(
            TVShowEntity(12,
            "The Good Doctor",
            "25 Sep 2017",
            8.6,
            "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg"))

        tvShows.add(
            TVShowEntity(13,
            "The Flash",
            "07 Oct 2014",
            7.7,
            "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg"))

        tvShows.add(
            TVShowEntity(14,
            "The Bad Batch",
            "04 May 2021",
            9.1,
            "Follow the elite and experimental Clones of the Bad Batch as they find their way in a rapidly changing galaxy in the aftermath of the Clone Wars.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/WjQmEWFrOf98nT5aEfUfVYz9N2.jpg"))

        tvShows.add(
            TVShowEntity(15,
            "The Handmaid's Tale",
            "26 Apr 2017",
            8.2,
            "Set in a dystopian future, a woman is forced to live as a concubine under a fundamentalist theocratic dictatorship. A TV adaptation of Margaret Atwood's novel.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oIkxqt6ug5zT5ZSUUyc1Iqopf02.jpg"))

        tvShows.add(
            TVShowEntity(16,
            "Grey's Anatomy",
            "27 Mar 2005",
            8.2,
            "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg"))

        tvShows.add(
            TVShowEntity(17,
            "Jupiter's Legacy",
            "07 May 2021",
            7.5,
            "When the world's first generation of superheroes received their powers in the 1930s become the revered elder guard in the present, their superpowered children struggle to live up to the legendary feats of their parents.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9yxep7oJdkj3Pla9TD9gKflRApY.jpg"))

        tvShows.add(
            TVShowEntity(18,
            "Luis Miguel: The Series",
            "22 Apr 2018",
            8.1,
            "The series dramatizes the life story of Mexican superstar singer Luis Miguel, who has captivated audiences in Latin America and beyond for decades.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/34FaY8qpjBAVysSfrJ1l7nrAQaD.jpg"))

        tvShows.add(
            TVShowEntity(19,
            "Lucifer",
                "25 Jan 2016",
            8.5,
            "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg"))

        tvShows.add(
            TVShowEntity(20,
            "Riverdale",
            "26 Jan 2017",
            8.6,
            "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg"))

        return tvShows
    }

    fun generateRemoteDummyTVShow(): ArrayList<TVShowEntity> {
        val tvShows = ArrayList<TVShowEntity>()

        tvShows.add(
            TVShowEntity(11,
                "The Falcon and the Winter Soldier",
                "19 Mar 2021",
                7.9,
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg"))

        tvShows.add(
            TVShowEntity(12,
                "The Good Doctor",
                "25 Sep 2017",
                8.6,
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg"))

        tvShows.add(
            TVShowEntity(13,
                "The Flash",
                "07 Oct 2014",
                7.7,
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg"))

        tvShows.add(
            TVShowEntity(14,
                "The Bad Batch",
                "04 May 2021",
                9.1,
                "Follow the elite and experimental Clones of the Bad Batch as they find their way in a rapidly changing galaxy in the aftermath of the Clone Wars.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/WjQmEWFrOf98nT5aEfUfVYz9N2.jpg"))

        tvShows.add(
            TVShowEntity(15,
                "The Handmaid's Tale",
                "26 Apr 2017",
                8.2,
                "Set in a dystopian future, a woman is forced to live as a concubine under a fundamentalist theocratic dictatorship. A TV adaptation of Margaret Atwood's novel.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oIkxqt6ug5zT5ZSUUyc1Iqopf02.jpg"))

        tvShows.add(
            TVShowEntity(16,
                "Grey's Anatomy",
                "27 Mar 2005",
                8.2,
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg"))

        tvShows.add(
            TVShowEntity(17,
                "Jupiter's Legacy",
                "07 May 2021",
                7.5,
                "When the world's first generation of superheroes received their powers in the 1930s become the revered elder guard in the present, their superpowered children struggle to live up to the legendary feats of their parents.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9yxep7oJdkj3Pla9TD9gKflRApY.jpg"))

        tvShows.add(
            TVShowEntity(18,
                "Luis Miguel: The Series",
                "22 Apr 2018",
                8.1,
                "The series dramatizes the life story of Mexican superstar singer Luis Miguel, who has captivated audiences in Latin America and beyond for decades.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/34FaY8qpjBAVysSfrJ1l7nrAQaD.jpg"))

        tvShows.add(
            TVShowEntity(19,
                "Lucifer",
                "25 Jan 2016",
                8.5,
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg"))

        tvShows.add(
            TVShowEntity(20,
                "Riverdale",
                "26 Jan 2017",
                8.6,
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg"))

        return tvShows
    }

    fun generateDummyDetailMovie(): MovieEntity {
        return MovieEntity(1,
            "Tom Clancy's Without Remorse",
            "29 Apr 2021",
            7.3,
            "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
            "Action, Thriller, War")
    }

    fun generateDummyRemoteDetailMovie(): MovieEntity {
        return MovieEntity(
            1,
            "Tom Clancy's Without Remorse",
            "29 Apr 2021",
            7.3,
            "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
            "Action, Thriller, War")
    }

    fun generateDummyDetailTV(): TVShowEntity {
        return TVShowEntity(11,
            "The Falcon and the Winter Soldier",
            "19 Mar 2021",
            7.9,
            "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            "Sci-Fi & Fantasy, Action & Adventure, Drama, War & Politics")
    }

    fun generateDummyRemoteDetailTV(): TVShowEntity {
        return TVShowEntity(11,
            "The Falcon and the Winter Soldier",
            "19 Mar 2021",
            7.9,
            "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
            "Sci-Fi & Fantasy, Action & Adventure, Drama, War & Politics")
    }
}