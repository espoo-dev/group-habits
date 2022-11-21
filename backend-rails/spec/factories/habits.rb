# == Schema Information
#
# Table name: habits
#
#  id         :bigint           not null, primary key
#  name       :string
#  created_at :datetime         not null
#  updated_at :datetime         not null
#  icon       :string
#  group_id   :integer
#
# Indexes
#
#  index_habits_on_group_id  (group_id)
#
FactoryBot.define do
  factory :habit do
    name { Faker::Esport.game }
  end
end
