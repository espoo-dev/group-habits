FactoryBot.define do
  factory :daily_habit do
    date { Time.zone.now }
    check { true }
    habit
  end
end
