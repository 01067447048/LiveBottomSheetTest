package com.jaehyeon.livebottomsheettest

/**
 * Created by Jaehyeon on 2022/09/05.
 */
enum class LifeType(val life: Int) {
    Easy15Days(5),
    Easy30Days(10),
    Easy50Days(13),
    Normal15Days(4),
    Normal30Days(8),
    Normal50Days(11),
    Hard15Days(3),
    Hard30Days(6),
    Hard50Days(9),
    None(-1)
}