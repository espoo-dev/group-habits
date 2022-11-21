# == Schema Information
#
# Table name: groups
#
#  id         :bigint           not null, primary key
#  name       :string           not null
#  created_at :datetime         not null
#  updated_at :datetime         not null
#
class Group < ApplicationRecord
  validates :name, presence: true

  has_many :users, dependent: :nullify
  has_many :habits, dependent: :nullify
  has_many :daily_habits, dependent: :destroy
end
