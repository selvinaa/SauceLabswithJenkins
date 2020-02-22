@bookingPage
Feature: Booking Pages Test

  @createCoupon @smoke
  Scenario: Verify coupon code
    Given User opens Admin home page
    When User logsin with admin account
    And User navigate to Coupons page
    And Creates a new coupon
    Then Verifies coupon is "created"
    Given User opens home page
    When User opens "first tour" featured tour
    And Clicks on "Book Now" button
    And Applies existing coupon code
    Then Verifies positive message is displayed

  @deleteCoupon
  Scenario: Verify coupon created and deleted
    Given User opens Admin home page
    When User logsin with admin account
    And User navigate to Coupons page
    And Creates a new coupon
    Then Verifies coupon is "created"
    And Deletes newly created coupon
    Then Verifies coupon is "deleted"

  @deleteCouponWithParameter
  Scenario: Verify coupon created and deleted
    Given User opens Admin home page
    When User logsin with admin account
    And User navigate to Coupons page
    And Creates a new coupon with following info
      | Key           | Value      |
      | percentage    | 45         |
      | max use       | 3          |
      | start date    | 28/01/2020 |
      | end date      | 30/01/2020 |
      | tour checkbox | check      |
      | car checkbox  | check      |
      | coupon code   | 45% coupon |
    Then Verifies coupon is "created"
    And Deletes newly created coupon
    Then Verifies coupon is "deleted"
