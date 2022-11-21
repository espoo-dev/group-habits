# == Schema Information
#
# Table name: groups
#
#  id         :bigint           not null, primary key
#  name       :string           not null
#  created_at :datetime         not null
#  updated_at :datetime         not null
#
require 'rails_helper'

RSpec.describe Group, type: :model do
  describe 'validates' do
    it { should validate_presence_of(:name) }
    it { should have_many(:users).dependent(:nullify ) }
    it { should have_many(:habits).dependent(:nullify ) }
    it { should have_many(:daily_habits).dependent(:destroy) }
  end
end
