# == Schema Information
#
# Table name: habits
#
#  id         :bigint           not null, primary key
#  name       :string
#  created_at :datetime         not null
#  updated_at :datetime         not null
#  icon       :string
#
require 'rails_helper'

RSpec.describe Habit, type: :model do
  describe 'relationships' do
    it { should have_many(:daily_habits).dependent(:destroy) }
  end

  describe 'validates' do
    it { should validate_presence_of(:name) }
  end
end
