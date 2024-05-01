//
// fmockup (Mock-up for Business System Demonstration and Development Templates)
//
// Date    : 2023-10-01
// Auther  : Hirotoshi FUJIBE
// History :
//
// Copyright (c) 2023 Hirotoshi FUJIBE
//

package cn.com.xxxx.fmockup.validator_order;

import jakarta.validation.GroupSequence;

@GroupSequence({ GroupOrder1.class, GroupOrder2.class, GroupOrder3.class })
public interface GroupOrder {
}
