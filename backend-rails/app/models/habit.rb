# == Schema Information
#
# Table name: habits
#
#  id         :bigint           not null, primary key
#  name       :string
#  created_at :datetime         not null
#  updated_at :datetime         not null
#
class Habit < ApplicationRecord
  validates :name, presence: true
end
