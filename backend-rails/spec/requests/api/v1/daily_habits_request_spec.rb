require 'rails_helper'

describe 'api/v1/daily_habits', type: :request do
  describe '#index' do
    let(:user) { create(:user) }
    let!(:daily_habits) { create_list(:daily_habit, 2, user:) }
    let!(:daily_habits2) { create_list(:daily_habit, 3) }

    before { get api_v1_daily_habits_path, headers: auth_headers, as: :json }
    it 'returns status 200 ok' do
      expect(response).to be_successful
    end

    it "returns user's daily_habits" do
      expect(json_response.pluck('id')).to match_array(daily_habits.pluck(:id))
    end
  end
end
