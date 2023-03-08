# == Schema Information
#
# Table name: unit_types
#
#  id         :bigint           not null, primary key
#  name       :string           not null
#  created_at :datetime         not null
#  updated_at :datetime         not null
#
# Indexes
#
#  index_unit_types_on_name  (name) UNIQUE
#
FactoryBot.define do
  factory :sales_unit do
    sequence(:name) { |n| "kilogram#{n}" }
  end
end
