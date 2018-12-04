package com.alex323glo.kpi.security.lab1

import com.alex323glo.kpi.security.lab1.data.Bit

fun getLFSR(feedbackFunction: List<Bit>, params: List<Bit>): LinearFeedbackShiftRegister =
        LinearFeedbackShiftRegister(feedbackFunction, params)