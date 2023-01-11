FactoryBot.define do
  factory :category do
    name { "equipment" }
    association :user
  end
end
